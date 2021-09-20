import React, { useContext, useEffect } from "react";
import { requestToken } from "src/common/api/axios";
import { AuthContext } from "src/common/context/AuthContext";
import { handleError } from "src/common/ultis";
import ChatContainer from "./ChatContainer";
import Navigator from "./Navigator";
import Sidebar from "./Sidebar";
import { SChat } from "./styles";

export default function Chat() {

  const {setInfo} = useContext(AuthContext)

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      requestToken({ method: "GET", url: "api/user" })
        .then((data) => {
          if(data?.data) {
            setInfo({...data.data})
          }
        })
        .catch((err) => {
          handleError(err);
        });
    }
  }, [setInfo]);

  return (
    <SChat>
      <Navigator />
      <Sidebar />
      <ChatContainer />
    </SChat>
  );
}
