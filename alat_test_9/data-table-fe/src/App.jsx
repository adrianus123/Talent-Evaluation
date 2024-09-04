import React, { useEffect, useMemo, useState, useRef } from "react";
import "ag-grid-community/styles/ag-grid.css";
import "ag-grid-community/styles/ag-theme-quartz.css";
import { AgGridReact } from "ag-grid-react";
import { getEmployees, saveData } from "./apis";
import {
  Box,
  Button,
  Container,
  Divider,
  Stack,
  Typography,
} from "@mui/material";

const App = () => {
  const valueParser = (params) => {
    return params.newValue;
  };

  const valueFormatter = (params) => {
    return params.value ? params.value.toString() : "";
  };

  const [refresh, setRefresh] = useState(true);
  const [insertData, setInsertData] = useState([]);
  const [updateData, setUpdateData] = useState([]);
  const [deleteData, setDeleteData] = useState([]);

  const [rowData, setRowData] = useState([]);
  const [colDefs, setColDefs] = useState([
    {
      field: "namaKaryawan",
      headerName: "Nama Karyawan",
      valueParser,
      valueFormatter,
    },
    { field: "jabatan", headerName: "Jabatan", valueParser, valueFormatter },
    {
      field: "departemen",
      headerName: "Departemen",
      valueParser,
      valueFormatter,
    },
    { field: "status", headerName: "Status", valueParser, valueFormatter },
  ]);

  const defaultColDef = useMemo(() => {
    return {
      flex: 1,
      editable: true,
      filter: true,
      floatingFilter: true,
    };
  });

  const gridRef = useRef();

  const onRemoveSelected = () => {
    const selectedNodes = gridRef.current.api.getSelectedNodes();
    const selectedData = selectedNodes.map((node) => node.data);

    selectedData.forEach((data) => {
      setDeleteData((prevDeleteData) => [data, ...prevDeleteData]);
    });

    const newData = rowData.filter((row) => !selectedData.includes(row));
    setRowData(newData);
  };

  useEffect(() => {
    const getData = async () => {
      const res = await getEmployees();
      setRowData(res.data);
    };

    if (refresh) {
      getData();
      setRefresh(false);
    }
  }, [refresh]);

  const onInsertOne = () => {
    const newRecord = {
      idKaryawan: rowData.length + 1,
      namaKaryawan: "",
      jabatan: "",
      departemen: "",
      status: "",
      isNew: true,
    };
    setRowData([newRecord, ...rowData]);
  };

  const onCellValueChanged = (params) => {
    console.log("Cell value changed:", params.data);

    if (params.data.isNew) {
      setInsertData((prevInsertData) => {
        const existingRowIndex = prevInsertData.findIndex(
          (row) => row.idKaryawan === params.data.idKaryawan
        );

        if (existingRowIndex >= 0) {
          const updatedInsertData = [...prevInsertData];
          updatedInsertData[existingRowIndex] = params.data;
          return updatedInsertData;
        }

        const insertData = [params.data, ...prevInsertData];
        console.log(insertData);
        return insertData;
      });
    } else {
      setUpdateData((prevUpdateData) => {
        const existingRowIndex = prevUpdateData.findIndex(
          (row) => row.idKaryawan === params.data.idKaryawan
        );

        if (existingRowIndex >= 0) {
          const updatedData = [...prevUpdateData];
          updatedData[existingRowIndex] = params.data;
          return updatedData;
        }

        // Menambahkan data baru ke daftar update
        return [params.data, ...prevUpdateData];
      });
    }
  };

  const save = async () => {
    try {
      const res = await saveData(insertData, updateData, deleteData);
      setInsertData([]);
      setUpdateData([]);
      setDeleteData([]);
      setRefresh(true);
      console.log(res);
    } catch (error) {
      throw error;
    }
  };

  return (
    <Container>
      <Stack spacing={2}>
        <Typography sx={{ fontSize: { xs: 24, sm: 32, md: 40, lg: 48 } }}>
          Daftar Karyawan
        </Typography>
        <Divider />
        <Box>
          <Stack direction="row" spacing={1} mb={2}>
            <Button
              variant="contained"
              color="primary"
              size="small"
              sx={{ textTransform: "capitalize", mb: 1 }}
              onClick={onInsertOne}
            >
              Insert Data
            </Button>
            <Button
              variant="contained"
              color="error"
              size="small"
              sx={{ textTransform: "capitalize", mb: 1 }}
              onClick={onRemoveSelected}
            >
              Remove Selected
            </Button>
            <Button
              variant="contained"
              color="success"
              size="small"
              sx={{ textTransform: "capitalize" }}
              onClick={save}
            >
              Save
            </Button>
          </Stack>
          <Box
            className="ag-theme-quartz"
            style={{ height: "100vh", width: "100%" }}
          >
            <AgGridReact
              ref={gridRef}
              rowData={rowData}
              rowSelection="multiple"
              columnDefs={colDefs}
              defaultColDef={defaultColDef}
              domLayout="autoHeight"
              pagination={true}
              paginationPageSize={10}
              paginationPageSizeSelector={[10, 20, 50]}
              onCellValueChanged={onCellValueChanged}
            />
          </Box>
        </Box>
      </Stack>
    </Container>
  );
};

export default App;
