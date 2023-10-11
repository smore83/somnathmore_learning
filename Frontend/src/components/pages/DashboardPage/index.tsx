import React from 'react'
import CustomTypograpy from '../../atoms/typograpy/CustomTypograpy'
import { Stack } from '@mui/material'
import CustomButton from '../../atoms/button/CustomButton'
import { useNavigate } from 'react-router-dom'
import { useAuth0 } from '@auth0/auth0-react'

const Dashboard= () => {
    const { logout } = useAuth0();
    const navigate=useNavigate();
  return (
    <>
    <Stack spacing={3}>
         <CustomTypograpy typoVariant={"body1"} >{"Dashboard with Auth 0 for SSO Integrations"}</CustomTypograpy>
        <CustomButton variant="contained" children={"Logout"} onClick={()=>navigate("/")}/>
      
        <CustomButton variant="contained" children={"Logout thorugh Auth0"} onClick={() => logout({ logoutParams: {returnTo: window.location.origin + '/' } })}/>
        </Stack>
    </>
  )
}

export default Dashboard