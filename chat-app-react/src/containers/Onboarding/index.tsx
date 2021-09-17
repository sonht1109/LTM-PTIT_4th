import { Button } from "antd";
import React, { useContext } from "react";
import { FaRocket } from "react-icons/fa";
import { Link } from "react-router-dom";
import { ThemeContext } from "styled-components";
import { SOnboarding } from "./styles";

export default function Onboarding() {
  const { theme } = useContext(ThemeContext);

  return (
    <SOnboarding>
      <img src="/images/onboarding.png" alt="onboarding" width={250} />
      <p>
        Connect people together{" "}
        <FaRocket style={{ marginLeft: "10px" }} color={theme.logo} />
      </p>
      <Link to="/login">
        <Button type="primary">Getting started !</Button>
      </Link>
    </SOnboarding>
  );
}
