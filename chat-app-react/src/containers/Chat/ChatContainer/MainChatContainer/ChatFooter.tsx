import { Button, Input } from "antd";
import React, { useContext, useEffect, useRef, useState } from "react";
import { FaPaperclip } from "react-icons/fa";
import { ThemeContext } from "styled-components";
import { SChatFooter } from "../styles";
import { BiSend } from "react-icons/bi";
import { useRouteMatch } from "react-router";
import { ReplyingContext } from "src/common/context/ReplyingContext";
import ReplyingSection from './ReplyingSection'

export default function ChatFooter() {

  const { theme } = useContext(ThemeContext);
  const {replying, setReplying} = useContext(ReplyingContext);

  const inputRef = useRef(null);

  const [message, setMessage] = useState("");

  const router = useRouteMatch();

  const reset = () => {
    const inputCurrent = inputRef?.current as any;
    if (inputCurrent) {
      inputCurrent?.focus();
    }
    setMessage("");
  };

  useEffect(() => {
    reset();
    if(replying) {
      setReplying(null);
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [router]);

  const onSubmit = () => {
    console.log(message);
    if (message.trim() !== "") {
      console.log("SUBMIT");
      reset();
    }
  };

  return (
    <>
    <ReplyingSection />
    <SChatFooter
      onSubmit={(e) => {
        e.preventDefault();
        onSubmit();
      }}
    >
      <Input.TextArea
        ref={inputRef}
        autoSize={{ minRows: 1, maxRows: 6 }}
        placeholder="Aa"
        className="footer-item"
        value={message}
        onChange={(e: any) => {
          setMessage(e.target.value);
        }}
        onKeyPress={(e: any) => {
          if (e.which === 13 && !e.shiftKey) {
            e.preventDefault();
            onSubmit();
          }
        }}
      />
      <label className="upload">
        <FaPaperclip
          size={16}
          style={{ minWidth: "16px" }}
          color={theme.text.main}
        />
        <input type="file" name="media" style={{ display: "none" }} />
      </label>
      <Button
        className="submit"
        type="primary"
        htmlType="submit"
        style={{ width: "32px", height: "32px" }}
      >
        <BiSend size={16} style={{ minWidth: "16px" }} color="white" />
      </Button>
    </SChatFooter>
    </>
  );
}
