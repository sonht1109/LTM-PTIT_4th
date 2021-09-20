import React from "react";
import { SLogo, SSignup } from "./styles";
import { FaRocket } from "react-icons/fa";
import { Button, Divider, Form, Input, message } from "antd";
import Title from "antd/lib/typography/Title";
import { Link, useHistory } from "react-router-dom";
import { request } from "src/common/api/axios";
import { handleError } from "src/common/ultis";

export default function Signup() {
  const history = useHistory();

  const onFinish = (values: any) => {
    request({
      method: "POST",
      url: "signup",
      data: {
        username: values.username,
        password1: values.password,
        password2: values.confirm_password,
      },
    })
      .then((data: any) => {
        console.log(data)
        if (data?.data.code === "200") {
          message.success("Register success");
          history.push("/login");
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
    <SSignup>
      <div className="wrapper">
        <SLogo>
          <FaRocket size={25} color="white" />
        </SLogo>
        <Title level={4}>Register</Title>
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
            rules={[
              { required: true, message: "Please input your username!" }
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            style={{ marginBottom: "15px" }}
            label="Password"
            name="password"
            rules={[
              { required: true, message: "Please input your password!" },
              { min: 6, message: "Password must be at least 6 chars" },
            ]}
          >
            <Input.Password />
          </Form.Item>

          <Form.Item
            label="Confirm password"
            name="confirm_password"
            rules={[
              { required: true, message: "Please input your password!" },
              { min: 6, message: "Password must be at least 6 chars" },
              ({ getFieldValue }) => ({
                validator(_, value) {
                  if (!value || getFieldValue("password") === value) {
                    return Promise.resolve();
                  }
                  return Promise.reject(new Error("Passwords do not match!"));
                },
              }),
            ]}
          >
            <Input.Password />
          </Form.Item>

          <Form.Item style={{ marginBottom: "10px" }}>
            <Button block type="primary" htmlType="submit">
              Register
            </Button>
          </Form.Item>

          <Divider plain>
            or{" "}
            <Link to="/login">
              <span style={{ fontWeight: 600 }}>Login</span>
            </Link>
          </Divider>
        </Form>
      </div>
    </SSignup>
  );
}
