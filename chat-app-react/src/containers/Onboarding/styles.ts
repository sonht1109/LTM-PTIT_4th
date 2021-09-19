import { mixinsFlexCenter } from "src/common/styles/mixins";
import styled from "styled-components";

export const SOnboarding = styled.div`
  background-image: url('/images/home-bg.svg');
  width: 100vw;
  height: 100vh;
  ${mixinsFlexCenter};
  background-size: cover;
  background-position: center;
  overflow: hidden;
  flex-direction: column;
  & > p {
    font-weight: 700;
    display: flex;
    align-items: center;
    margin-top: 20px;
    font-size: 24px;
    margin-bottom: 30px;
    color: #828282;
  }
`