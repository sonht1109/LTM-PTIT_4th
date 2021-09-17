import { Button } from "antd";
import Avatar from "antd/lib/avatar/avatar";
import React, { useContext } from "react";
import { FaArrowLeft, FaCamera } from "react-icons/fa";
import { useHistory } from "react-router";
import { ThemeContext } from "styled-components";
import { SProfile } from "./styles";

export default function Profile() {
  const { theme } = useContext(ThemeContext);
  const history = useHistory();

  return (
    <SProfile>
      <FaArrowLeft
        color={theme.icon.inactive}
        className="back"
        onClick={() => history.goBack()}
        size={30}
      />
      <label className="avt">
        <Avatar src="/images/avt-placeholder.png" size={80} />
        <FaCamera color={theme.icon.inactive} className="icon" size={20} />
        <input
          type="file"
          accept="image/png, image/jpeg"
          style={{ display: "none" }}
        />
      </label>
      <div className="name">@sonht</div>
      <Button style={{ minWidth: "100px" }} type="primary">
        Save
      </Button>
    </SProfile>
  );
}
