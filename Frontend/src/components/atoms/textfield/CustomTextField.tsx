import { InputProps, TextField, TextFieldProps } from '@mui/material'
import React from 'react'
export interface MuiTextFieldProps extends Omit<TextFieldProps, "variant">{
    id?:string;
    customInputProps?: InputProps;
}
const CustomTextField :React.FC<MuiTextFieldProps> = (props) => {
  return (
    <TextField {...props}>{props.children}</TextField>
  )
}

export default CustomTextField