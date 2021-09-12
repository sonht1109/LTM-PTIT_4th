import { mixinsScrollBar } from "src/common/styles/mixins";
import styled from "styled-components";

export const SChatboxInfo = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 0;
  bottom: 0;
  width: 300px;
  z-index: 5;
  height: 100%;
  right: ${(props) => (props.isOpen ? "0px" : "-300px")};
  transition: 0.2s;
  background-color: ${(props) => props.theme.theme.bg.main};
  overflow: auto;
  ${(props) =>
    mixinsScrollBar(
      "3px",
      "3px",
      "3px",
      "transparent",
      props.theme.theme.border
    )};
`;
