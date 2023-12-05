import React, { useState } from 'react';
// import {  } from "@emotion/react";
import { Box, Button, Stack, TextField, Typography} from "@mui/material";
import styled from '@emotion/styled';
import { Post_Login } from '../services';
import { error } from 'console';

const FormContainer=styled(Stack)({
      gap:'20px',
      display:'flex',
      height:'400px',
      width:'400px',
      padding:'20px'
})
const LoginForm = () => {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [showToast, setShowToast] = useState(false);

  const handleLogin = () => {
    // Perform login logic here (e.g., API call, authentication check)
    // For this example, let's just show the toast for a successful login
    const data={
      userName:userName,
      password:password
    }
    console.log(data);
    Post_Login(data).then((res:any)=>{
      if(res=="Successfully Logged In!"){
        setShowToast(true);
       
      }else{
        alert("Invalid password");
        console.log("Invalid Credentials");
      }
      setPassword('');
      setUserName('');
    }).catch((error:any)=>{
      console.log(error);
    })
 

    // You may want to redirect the user or perform other actions upon successful login
  };

  return (
    <div>
      <h2>Login</h2>
      <FormContainer>
        <Stack >
          <Typography variant='body1'>Username:</Typography>
          <TextField
            type="text"
            placeholder='password'
            size='small'
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
            required
          />
        </Stack>
        <Stack>
          <Typography variant='body1'>Password:</Typography>
          <TextField
            type="text"
            placeholder='password'
            size='small'
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </Stack>
        <Button  variant="contained" onClick={handleLogin}>
          Login
        </Button>
        </FormContainer>

      {showToast && (
        <div style={{ position: 'fixed', top: '20px', right: '20px', background: '#4CAF50', color: '#fff', padding: '15px', borderRadius: '5px' }}>
          Successfully logged in!
        </div>
      )}
    </div>
  );
};

export default LoginForm;


