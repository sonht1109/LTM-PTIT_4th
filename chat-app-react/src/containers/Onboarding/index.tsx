import { Button } from "antd";
import { FaRocket } from "react-icons/fa";
import { Link } from "react-router-dom";
import { SOnboarding } from "./styles";

export default function Onboarding() {

  return (
    <SOnboarding>
      <img src="/images/onboarding.png" alt="onboarding" width={250} />
      <p>Welcome !</p>
      <Link to="/login">
        <Button type="primary">
          Start chatting !
          <FaRocket style={{ marginLeft: "10px" }} color="white" />
        </Button>
      </Link>
    </SOnboarding>
  );
}
