import React from "react";

import PrivacyPolicy from "../../../src/components/images/computer.jpeg";
import { Grid,  styled } from "@mui/material";
import CustomIcon from "../atoms/icon/CustomIcon";


const TemplateContainer = styled(Grid)({
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  height: "100vh",
  // backgroundColor: "#F7F8FA",
  background: 'lightgrey'
});

const LeftContainer = styled(TemplateContainer)({
  // [theme.breakpoints.down("md")]: {
  //   display: "none",
  // },
});

interface SignInTemplateProps {
  rightComponent?: React.ReactNode;
}

const SignInTemplate = ({ rightComponent }: SignInTemplateProps) => {
  return (
    <Grid container>
      <LeftContainer item xs={6} md={6}>
        <CustomIcon src={PrivacyPolicy} alt="left side image" />
      </LeftContainer>
      <TemplateContainer item xs={12} md={6}>
        {rightComponent ?? "Right Side Component"}
      </TemplateContainer>
    </Grid>
  );
};

export default SignInTemplate;
