import { message } from "antd";
import { Redirect, Route, RouteProps } from "react-router";

export default function ProtectedRoute({ children, ...rest }: RouteProps) {
  if (!localStorage.getItem("token")) {
    message.error("Vui lòng đăng nhập lại");
    return <Redirect to="/login" />;
  }
  return <Route {...rest}>{children}</Route>;
}
