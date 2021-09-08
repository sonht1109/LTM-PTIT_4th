import styled, { css } from "styled-components";

export const SNavigator = styled.div`
  color: ${(props) => props.theme.theme.logo};
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 80px;
  border-right: ${(props) => `1px solid ${props.theme.theme.border}`};
  padding: 15px;
  min-height: 500px;
  overflow: auto;
  /* background-image: url('/images/chat-bg.png'); */
  .logo {
    margin-bottom: 20px;
  }
  .list {
    flex-grow: 1;
  }

`;

export const SHandleButton = styled.div<{ active: boolean }>`
  width: 50px;
  height: 35px;
  border-radius: 4px;
  background-color: ${(props) =>
    props.active ? props.theme.theme.bg.icon : "transparent"};
  transition: 0.2s;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  margin: 15px 0;
  ${(props) =>
    !props.active &&
    css`
      &:hover {
        background-color: ${(props) => props.theme.theme.bg.hover};
      }
    `}
`;
