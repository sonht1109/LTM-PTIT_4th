import React from "react";
import { SLogin } from "./styles";
import { FaRocket } from "react-icons/fa";
import { Button, Divider, Form, Input, message } from "antd";
import Title from "antd/lib/typography/Title";
import { Link, useHistory } from "react-router-dom";
import { SLogo } from "../Signup/styles";
import { request } from "src/common/api/axios";
import { handleError } from "src/common/ultis";

export default function Login() {

  const history = useHistory();
  
  const onFinish = (values: any) => {
    request({
      method: "POST",
      url: "login",
      data: {
        username: values.username,
        password: values.password,
      },
    })
      .then((data: any) => {
        if (data?.data.code === "200") {
          localStorage.setItem("token", data.data.body.split("CHAT_APP ")[1]);
          history.push("/c");
        } else {
          message.error(data?.data.message);
        }
      })
      .catch((err) => {
        handleError(err);
      });
  };

  const onFinishFailed = (errorInfo: any) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <SLogin>
      <div className="wrapper">
        <SLogo>
          <FaRocket
            size={25}
            color="white"
          />
        </SLogo>
        <Title level={4}>Login</Title>
        <Form
          name="basic"
          layout="vertical"
          initialValues={{ remember: true }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          autoComplete="off"
        >
          <Form.Item
            style={{ marginBottom: "15px" }}
            label="Username"
            name="username"
            rules={[{ required: true, message: "Please input your username!" }]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            style={{ marginBottom: "15px" }}
            label="Password"
            name="password"
            rules={[{ required: true, message: "Please input your password!" }]}
          >
            <Input.Password />
          </Form.Item>

          <Form.Item style={{ marginBottom: "10px" }}>
            <Button block type="primary" htmlType="submit">
              Login
            </Button>
          </Form.Item>

          <Divider plain>
            or <Link to="/signup"><span style={{fontWeight: 600}}>Register</span></Link>
          </Divider>
        </Form>
      </div>
    </SLogin>
  );
}
