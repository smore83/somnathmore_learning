import { Button } from '@mui/material'
import { ButtonProps } from '@mui/material/Button'
import React from 'react'
interface CustomButtonProps extends ButtonProps{

}
const CustomButton = ({...buttonProps}:CustomButtonProps) => {
  return (
    <Button  {...buttonProps}/>
  )
}

export default CustomButton