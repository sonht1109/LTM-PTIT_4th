import { Button } from "antd";
import Avatar from "antd/lib/avatar/avatar";
import React, { useContext, useEffect, useState } from "react";
import { FaArrowLeft, FaCamera } from "react-icons/fa";
import { useHistory } from "react-router";
import { AuthContext } from "src/common/context/AuthContext";
import { avatarSrc } from "src/common/ultis";
import { ThemeContext } from "styled-components";
import { SProfile } from "./styles";

export default function Profile() {
  const { theme } = useContext(ThemeContext);
  const { info } = useContext(AuthContext);
  const history = useHistory();

  const [imgSrc, setImgSrc] = useState<string | null>(null);

  const onChange = (e: any) => {
    const imgSrc = URL.createObjectURL(e.target.files[0]);
    setImgSrc(imgSrc);
  };

  useEffect(() => {
    if (info?.avatar) {
      setImgSrc(info.avatar);
    }
  }, [info]);

  return (
    <SProfile>
      <FaArrowLeft
        color={theme.icon.inactive}
        className="back"
        onClick={() => history.goBack()}
        size={30}
      />
      <label className="avt">
        <Avatar src={avatarSrc(imgSrc || "")} size={80} />
        <FaCamera color={theme.icon.inactive} className="icon" size={20} />
        <input
          type="file"
          onChange={onChange}
          accept="image/png, image/jpeg"
          name="avt"
          style={{ display: "none" }}
        />
      </label>
      <div className="name">@{info?.username}</div>
      <Button style={{ minWidth: "100px" }} type="primary">
        Save
      </Button>
    </SProfile>
  );
}
