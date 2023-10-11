import API from "./API"

export const authenticateUser = async (email:any,password:any) => {
    const data = {
        email: email,
        password: password
      };
    return await API.post('users/login', data)
  }
  

export const registerUser = async (data: any) => {
  return await API.post('users/register', data)
}