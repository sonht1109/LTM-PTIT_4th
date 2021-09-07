import React from "react";
import styled from "styled-components";

export default function IconButton({
  children,
  onClick,
}: {
  children: any;
  onClick?: () => void;
}) {
  return <SIconButton onClick={onClick}>{children}</SIconButton>;
}

const SIconButton = styled.div`
  cursor: pointer;
  display: inline-block;
  width: 40px;
  height: 35px;
  border-radius: 4px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 4px 4px;
  background-color: ${props => props.theme.theme.bg.hover};
`;
