import React from "react";
import { SSignup } from "./styles";
import { FaPaperPlane } from "react-icons/fa";
import { Button, Divider, Form, Input, message } from "antd";
import Title from "antd/lib/typography/Title";
import { Link } from "react-router-dom";

export default function Signup() {
  const onFinish = (values: any) => {
    console.log("Success:", values);
    message.success("Login success");
  };

  const onFinishFailed = (errorInfo: any) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <SSignup>
      <div className="wrapper">
        <FaPaperPlane
          size={30}
          style={{ marginBottom: "25px" }}
          color="#1890ff"
        />
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
            rules={[{ required: true, message: "Please input your username!" }]}
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
            or <Link to="/login">Login</Link>
          </Divider>
        </Form>
      </div>
    </SSignup>
  );
}
