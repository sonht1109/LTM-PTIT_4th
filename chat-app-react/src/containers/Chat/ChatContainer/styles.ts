import { mixinsFlexCenter, mixinsScrollBar } from "src/common/styles/mixins";
import styled, { css } from "styled-components";

export const SChatContainer = styled.div`
  flex-grow: 1;
  height: 100%;
  display: flex;
  border-left: ${(props) => `1px solid ${props.theme.theme.border}`};
`;

export const SMainChatContainer = styled(SChatContainer)`
  flex-direction: column;
`;

export const SChatHeader = styled.div`
  height: 80px;
  padding: 15px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid ${(props) => props.theme.theme.border};
  width: 100%;
  .avt {
    position: relative;
  }
  .detail {
    margin-left: 15px;
    flex-grow: 1;
    .name {
      color: ${(props) => props.theme.theme.text.main};
      font-weight: 600;
      font-size: 18px;
    }
  }
  .group-button {
    margin: 0 -4px -4px;
  }
`;

export const SChatBody = styled.div`
  flex-grow: 1;
  background-image: url("/images/chat-bg.png");
  background-color: ${(props) => props.theme.theme.bg.chat};
  background-repeat: no-repeat;
  display: flex;
  flex-direction: column-reverse;
  overflow-y: auto;
  padding: 15px;
  ${(props) =>
    mixinsScrollBar(
      "3px",
      "3px",
      "3px",
      "transparent",
      props.theme.theme.border
    )};
`;

export const SMessage = styled.div<{ fromMe: boolean }>`
  display: flex;
  align-items: flex-end;
  max-width: 80%;
  margin-bottom: 4px;
  position: relative;
  padding-left: 46px;
  ${(props) =>
    props.fromMe &&
    css`
      margin-left: auto;
    `}
  &.is_first {
    .detail {
      .name {
        display: block;
      }
    }
  }
  &.is_last {
    .ant-avatar {
      display: block;
    }
  }
  .ant-avatar {
    display: none;
    position: absolute;
    bottom: 0;
    left: 0;
  }
  .detail {
    .name {
      display: none;
    }
    .name {
      margin-bottom: 4px;
      font-size: 12px;
      font-weight: 500;
      color: ${(props) => props.theme.theme.text.main};
    }
    .timestamp {
      text-align: right;
      margin-bottom: 0;
      margin-top: -4px;
      & > span{
        font-size: 10px;
        margin-left: 4px;
      }
    }
    .content {
      position: relative;
      margin-bottom: 0;
      color: ${(props) =>
        props.fromMe
          ? props.theme.theme.text.message.send
          : props.theme.theme.text.message.receive};
      padding: 6px 8px;
      border-radius: 4px;
      background-color: ${(props) =>
        props.fromMe
          ? props.theme.theme.bg.message.send
          : props.theme.theme.bg.message.receive};
    }
  }
`;

export const SChatFooter = styled.form`
  min-height: 70px;
  display: flex;
  align-items: center;
  padding: 15px;
  box-sizing: border-box;
  border-top: 1px solid ${(props) => props.theme.theme.border};
  margin: 0 -4px;

  textarea.ant-input {
    margin: 0 4px;
    flex-grow: 1;
    border: 1px solid ${(props) => props.theme.theme.border};
    background-color: ${(props) => props.theme.theme.bg.main};
    color: ${(props) => props.theme.theme.text.main};
  }

  .upload,
  .submit {
    width: 32px;
    min-width: 32px;
    height: 32px;
    ${mixinsFlexCenter};
    margin: 0 4px;
    border-radius: 4px;
  }

  .upload {
    background-color: ${(props) => props.theme.theme.bg.hover};
    border: none;
  }
`;