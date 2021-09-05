import styled from "styled-components";

export const SSidebar = styled.div`
  color: ${(props) => props.theme.theme.logo};
  position: fixed;
  display: flex;
  flex-direction: column;
  align-items: center;
  left: 0;
  top: 0;
  bottom: 0;
  width: 80px;
  border-right: ${(props) => `1px solid ${props.theme.theme.border}`};
  padding: 20px 15px;
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
  &:hover {
    background-color: ${(props) => props.theme.theme.bg.hover};
  }
`;
