import axios from "axios"

export const Post_Login = async (data:{userName:string,password:string}) => {
    try {
      const res = await axios.post(`http://localhost:8080/owsap/auth?userName=${data.userName}&password=${data.password}`);
      console.log("service Call"+data);
      console.log(res);
      console.log(res.status);
      return res.data;

    } catch (error) {
      // Handle errors here
      console.error('Error:', error);
      throw error;
    }
  };

