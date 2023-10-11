import SignUp from "../../organisms/SignUpCard";
import SignInTemplate from "../../templates/SignInTemplate";

const SignUpPage = () => {
    return (
      <SignInTemplate rightComponent={<SignUp />}/>
    )
  }
  
  export default SignUpPage;