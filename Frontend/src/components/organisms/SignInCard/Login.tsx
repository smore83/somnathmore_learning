import styled from '@emotion/styled'
import { Divider, Grid, InputAdornment, Paper, Stack } from '@mui/material'
import React, { useState } from 'react'
import CustomTextField from '../../atoms/textfield/CustomTextField'
import CustomTypograpy from '../../atoms/typograpy/CustomTypograpy'
import CustomButton from '../../atoms/button/CustomButton'
import CustomIcon from '../../atoms/icon/CustomIcon'
import google_icon from '../../../components/images/logogoogle.svg'
import github_icon from '../../../components/images/githublogo.svg';
import { channel } from 'diagnostics_channel'
import { Link, useNavigate } from 'react-router-dom'
import open from '../../../components/images/openEye.svg';
import close from '../../../components/images/closeEye.svg';
import { Auth0Provider, useAuth0 } from '@auth0/auth0-react'
import { authenticateUser } from '../../service'
import axios from 'axios'
const OuterGrid = styled(Paper)({
    // display:"grid",
    margin: "250px",

    width: "480px",
    height: "672px",
    padding: "25px",

    background: 'lightgrey'
})
const Login = () => {
    const {loginWithRedirect}=useAuth0();
    const navigate=useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [emailerror, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const [showPassword, setShowPassword] = useState<boolean>(false);
    const emailRegex = /^[^\s@]+@[^.@]+\.[^\s@.]+$/;
    const passwordRegex = /^[a-zA-Z0-9]{4,16}$/;
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
    const handleEmailChange = (e: any) => {
        console.log(e.target.value);
        setEmail(e.target.value);
        validateEmail(e.target.value)
    }
    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handlePasswordChange = (e: any) => {
        console.log(e.target.value);
        setPassword(e.target.value);
        validatePassword(e.target.value)
    }
    const handleLoginform = () => {
        
        if(email && password && !passwordError && !emailerror){
            alert("Form submmitted Successffully");
            
           authenticateUser(email ,password)
            .then(async (res) => {
              if (res && res.data && res.data.token) {
                const token = res.data.token;
                console.log(token);
                localStorage.setItem('accessToken', token);
                navigate('/dashboard');
              }
            })
         
        }
    }

    interface EndAdormentProps {
        handleClickShowPassword: () => void;
        showPassword: boolean;
      }
      
      const EndAdorment = ({
        handleClickShowPassword,
        showPassword,
      }: EndAdormentProps) => {
        return (
          <InputAdornment position="end">
            <CustomButton
              aria-label="toggle password visibility"
              data-testid="visibleIcon"
              onClick={handleClickShowPassword}
             
            >
              <CustomIcon
                src={showPassword ? open : close}
                alt={showPassword ? "VisibleIcon" : "InvisibleIcon"}
              />
            </CustomButton>
          </InputAdornment>
        );
      };
      
      const redirectUri = window.location.origin + '/dashboard'
  const buttonColor=!emailerror && !passwordError?"green":"red";

  const handleAuthLogin = () => {
    const redirectUri = window.location.origin + '/dashboard'
    loginWithRedirect({
      appState: {
        returnTo: '/dashboard',
      },
      authorizationParams: {
        connection: 'google-oauth2',
        redirect_uri: redirectUri,
      },
    })
  }
    return (
        <>
            <OuterGrid >
                <CustomTypograpy variant="h5" typoVariant={"h5"} children={"Login"} />

                <Stack spacing={3} style={{ marginTop: "15px" }}>
                    <CustomTypograpy variant="body1" typoVariant={"body1"} children={"Email"} />

                    <CustomTextField type='email' value={email} helperText={<CustomTypograpy typoVariant={"body1"} style={{ color: "red" }} children={emailerror} />} placeholder='enter email id' onChange={handleEmailChange} size='small' style={{ width: "450px" }} />

                </Stack>
                <Stack spacing={3} style={{ marginTop: "15px" }}>
                    <CustomTypograpy variant="body1" typoVariant={"body1"} children={"Password"} />
                    <CustomTextField type='password' helperText={<CustomTypograpy typoVariant={"body1"} 
                    style={{ color: "red" }} children={passwordError} />} 
                    value={password} placeholder='Password' onChange={handlePasswordChange} size='small'
                     style={{ width: "450px" }} 
                     InputProps={{
                        type: showPassword ? "text" : "password",
                        endAdornment: (
                          <EndAdorment
                            handleClickShowPassword={handleClickShowPassword}
                            showPassword={showPassword}
                          />
                        ),
                      }}
                     />
                    <CustomButton variant="contained" onClick={handleLoginform} 
                    style={{ background:`${buttonColor}`, width: "450px" }} children={"Login"}
                     size="large" />
                    <Grid container style={{ justifyContent: "end"}}>
                        <CustomTypograpy typoVariant="body1" style={{ color: "blue" }} sx={{ cursor: "pointer" }}>
                            Forgot Password?
                        </CustomTypograpy>
                    </Grid>
                    <Divider style={{ width: "450px"}} >
                        <CustomTypograpy
                            typoVariant="body2"
                            color={"blue"}
                            children={"or"}
                        />
                    </Divider>
                    

                <CustomButton
                    variant={"contained"}
                    children={
                        <CustomTypograpy
                            typoVariant="body2"
                            children={"Sign In with Google"}
                        />
                    }
                    startIcon={<CustomIcon src={google_icon} alt={"GoogleIcon"} />}
                    sx={{
                        width: "450px",
                        height: "48px",
                        border: `1px solid lightpink`,
                        padding: "2px 4px 2px 4px",
                        textTransform: "none",
                        borderRadius: 1,
                    }}
                    style={{
                        color: "blue" ?? "",
                        backgroundColor: "goldenrod",
                    }}
                  
                    onClick={handleAuthLogin }
                />


                <CustomButton
                    variant={"contained"}
                    children={
                        <CustomTypograpy
                            typoVariant="body2"
                            children={"Sign In with Github"}
                        />
                    }
                    startIcon={<CustomIcon src={github_icon} alt={"GoogleIcon"} />}
                    sx={{
                        width: "450px",
                        height: "48px",
                        border: `1px solid lightpink`,
                        padding: "2px 4px 2px 4px",
                        textTransform: "none",
                        borderRadius: 1,
                    }}
                    style={{
                        color: "blue" ?? "",
                        backgroundColor: "goldenrod",
                    }}
                    onClick={() => console.log("Hellow gihub")
                        //   loginWithRedirect({
                        //     // appState: { returnTo: "/candidates" },
                        //     authorizationParams: { connection: "google-oauth2" },
                        //   })
                    }
                />

                  <Grid container style={{justifyContent:"end"}}>
                  <Link to="/signup">
                <CustomTypograpy typoVariant={"body1"} children={"Need an Account ?Sign Up"} />
                </Link>
                </Grid>
                 </Stack>
            </OuterGrid>
        </>
    )
}

export default Login