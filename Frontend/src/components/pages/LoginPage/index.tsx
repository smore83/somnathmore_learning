import React from 'react'
import Login from '../../organisms/SignInCard/Login';
import SignInTemplate from '../../templates/SignInTemplate';


const LoginPage = () => {
  return (
    <SignInTemplate rightComponent={<Login />}/>
  )
}

export default LoginPage;