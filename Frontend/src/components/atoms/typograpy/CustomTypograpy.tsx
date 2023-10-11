import { Typography, TypographyProps } from '@mui/material'
import React from 'react'
interface CustomTypograpyProps extends TypographyProps{
    typoVariant:
    | "h1"
    | "h2"
    | "subtitle1"
    | "body1"
    | "body2"
    | "caption1"
    | "caption2"
    | "caption3"
    |any;
}

const CustomTypograpy:React.FC<CustomTypograpyProps> = ({typoVariant,...typoProps}:CustomTypograpyProps) => {
  return (
    <Typography variant={typoVariant} {...typoProps}/>
  )
}

export default CustomTypograpy