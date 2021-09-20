import styled from "styled-components";

const NoData = styled.p<{textCenter: boolean}>`
  font-size: 14px;
  line-height: 20px;
  margin-top: 10px;
  color: ${props => props.theme.theme.text.sub};
  text-align: ${props => props.textCenter ? 'center' : 'left'};
`

export default NoData;