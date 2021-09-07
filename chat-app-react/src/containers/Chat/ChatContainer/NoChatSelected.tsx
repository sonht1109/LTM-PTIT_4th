import React, { useContext } from 'react'
import { FaRocket } from 'react-icons/fa'
import styled, { ThemeContext } from 'styled-components'
import { SChatContainer } from './styles'

export default function NoChatSelected() {

  const {theme} = useContext(ThemeContext);

  return (
    <SNoChat>
      <span>Let's have a chat !</span> <FaRocket size={18} color={theme.text.main} />
    </SNoChat>
  )
}

const SNoChat = styled(SChatContainer)`
  justify-content: center;
  align-items: center;
  & > span {
    color: ${props => props.theme.theme.text.main};
    font-size: 16px;
    margin-right: 8px;
  }
`