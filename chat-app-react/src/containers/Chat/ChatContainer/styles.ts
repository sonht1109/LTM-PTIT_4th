import styled from "styled-components";

export const SChatContainer = styled.div`
  flex-grow: 1;
  height: 100%;
  display: flex;
  border-left: ${props => `1px solid ${props.theme.theme.border}`};
`

export const SMainChatContainer = styled(SChatContainer)``

export const SChatHeader = styled.div`
  height: 80px;
  padding: 15px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid ${props => props.theme.theme.border};
  width: 100%;
  .avt {
    position: relative;
    
  }
  .detail {
    margin-left: 15px;
    flex-grow: 1;
    .name {
      color: ${props => props.theme.theme.text.main};
      font-weight: 600;
      font-size: 18px;
    }
  }
  .group-button {
    margin: 0 -4px -4px;
  }
`

export const SChatBody = styled.div``

export const SChatFooter = styled.div``