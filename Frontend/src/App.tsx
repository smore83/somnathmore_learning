import React, { useEffect, useState } from 'react';
import Login from './components/organisms/SignInCard/Login';
import LoginPage from './components/pages/LoginPage';
import SignUpPage from './components/pages/SignUpPage';
import Dashboard from './components/pages/DashboardPage';
import { Navigate, Route, Routes, useNavigate } from 'react-router-dom';
import { useAuth0 } from '@auth0/auth0-react';



function App() {
  const navigate=useNavigate();
  const { user, isAuthenticated} = useAuth0();
  const [accessToken, setAccessToken] = useState(
    localStorage.getItem('accessToken')
  )

  useEffect(() => {
    const token = localStorage.getItem('accessToken')
    setAccessToken(token)
  }, [])

const token=localStorage.getItem("accessToken");
  return (
    <>
    <Routes>
      <Route path='/' element={<LoginPage/>}/>
      <Route path='/signup' element={<SignUpPage/>}/>
      <Route path='/dashboard' element={  accessToken?<Dashboard/>:<Navigate to="/" />}/>
    
   
    </Routes>
    </>
  );
}

export default App;
