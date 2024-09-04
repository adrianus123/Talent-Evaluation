import axios from "axios";

const BASE_URL = "http://localhost:8080/api/employees";

export const getEmployees = async () => {
  try {
    const res = await axios.get(`${BASE_URL}`);
    return res.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

export const saveData = async (insertData, updateData, deleteData) => {
  try {
    const data = [
      {
        operationType: "CREATE",
        employees: insertData || [],
      },
      {
        operationType: "UPDATE",
        employees: updateData || [],
      },
      {
        operationType: "DELETE",
        employees: deleteData || [],
      },
    ];

    const res = await axios.post(`${BASE_URL}/save`, data);
    return res.data;
  } catch (error) {
    throw error;
  }
};
