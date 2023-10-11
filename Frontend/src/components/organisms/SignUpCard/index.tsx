import styled from '@emotion/styled'
import { Grid, Paper, Stack } from '@mui/material'
import React, { useState } from 'react'
import CustomTypograpy from '../../atoms/typograpy/CustomTypograpy'
import CustomButton from '../../atoms/button/CustomButton'
import CustomTextField from '../../atoms/textfield/CustomTextField'
import { useNavigate } from 'react-router-dom'
import { registerUser } from '../../service'
const OuterGrid=styled(Paper)({
  


     width: "480px",
     height: "672px",
    //  background:"lightgrey",
     padding:"25px"
})

const SignUp = () => {
    const navigate=useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [emailerror, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const emailRegex = /^[^\s@]+@[^.@]+\.[^\s@.]+$/;
    const passwordRegex = /^[a-zA-Z0-9]{6,16}$/;


 
    const validateEmail = (email: string) => {
        if (!email) {
            setEmailError("more than 7 characters")
        } else if (!emailRegex.test(email)) {
            setEmailError("Invalid format")
        }
        else if (email.length < 7) {
            setEmailError("more than 7 characters")
        } else {
            setEmailError("")
        }
    }
    const validatePassword = (password: string) => {
        if (!password) {
            setPasswordError("Password is required");
        } else if (!passwordRegex.test(password)) {
            setPasswordError("Password must be at least 8 characters long");
        } else {
            setPasswordError("");
        }
    }
    const handleEmailChange=(e:any)=>{
           const emailValue=e.target.value;
           setEmail(emailValue);
           validateEmail(emailValue);
    }
    const handlePasswordChange=(e:any)=>{
        const passwordValue=e.target.value;
        setPassword(passwordValue);
        validatePassword(passwordValue)
        
    }
    const handleSignUpForm=()=>{
     
           const userDetails= {
               email: email,
               password: password
             };
           registerUser(userDetails)
           .then(async (res) => {
             navigate('/dashboard')
           })
           .catch((error) => {
             console.log(error)
           })
        
   }
  return (
    <OuterGrid>
        <Stack spacing={3}>
          <CustomTypograpy typoVariant={"h4"} children="SignUpScreen"/>
            <CustomTypograpy typoVariant={"body1"} children="Email"/>
            <CustomTextField  helperText={<CustomTypograpy typoVariant={"body1"} children={emailerror}/>} size='small' value={email} placeholder='enter email' type='email' onChange={handleEmailChange} />
            <CustomTypograpy typoVariant={"body1"} children="Password"/>
            <CustomTextField helperText={<CustomTypograpy typoVariant={"body1"} children={passwordError}/>} size='small' value={password} placeholder='enter Password' type='password' onChange={handlePasswordChange} />
            <CustomButton variant="contained" children="Sign Up" onClick={handleSignUpForm}/>
            <Grid container style={{justifyContent:"center"}}  >
            
            <CustomTypograpy typoVariant={"body1"} onClick={()=>navigate("/")} children="Already have an Account. Login Here"/>
          
            </Grid>
        </Stack>

    </OuterGrid>
  )
}

export default SignUp