import React from 'react'
import CustomTypograpy from '../../atoms/typograpy/CustomTypograpy'
import { Grid, Paper, Stack } from '@mui/material'
import CustomButton from '../../atoms/button/CustomButton'
import { useNavigate } from 'react-router-dom'
import { useAuth0 } from '@auth0/auth0-react'
import styled from '@emotion/styled'

const OuterGrid=styled(Paper)({
  background:'lightgrey',
  height:'99vh',
  width:'100%'
  
})
const Dashboard= () => {
    const { logout } = useAuth0();
    const navigate=useNavigate();
  return (
    <>
    <OuterGrid>
    <Stack spacing={3}>
          <Grid container style={{justifyContent:'center'}}>
         <CustomTypograpy typoVariant={"h4"} >{"Dashboard with Auth 0 for SSO Integrations"}</CustomTypograpy>
         </Grid>
        <CustomButton variant="contained" children={"Logout"} onClick={()=>navigate("/")}/>
      
        <CustomButton variant="contained" children={"Logout thorugh Auth0"} onClick={() => logout({ logoutParams: {returnTo: window.location.origin + '/' } })}/>
     
        </Stack>
        </OuterGrid>
    </>
  )
}

export default Dashboard