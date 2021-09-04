import React from "react";
import { SLogin } from "./styles";
import { FaPaperPlane } from "react-icons/fa";
import { Button, Divider, Form, Input } from "antd";
import Title from "antd/lib/typography/Title";
import { Link } from "react-router-dom";

export default function Login() {
  const onFinish = (values: any) => {
    console.log("Success:", values);
  };

  const onFinishFailed = (errorInfo: any) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <SLogin>
      <div className="wrapper">
        <FaPaperPlane
          size={30}
          style={{ marginBottom: "25px" }}
          color="#1890ff"
        />
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

          <Divider plain>or <Link to="/signup">Register</Link></Divider>
        </Form>
      </div>
    </SLogin>
  );
}
