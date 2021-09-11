import React from 'react'
import ChatContainer from './ChatContainer'
import Navigator from './Navigator'
import Sidebar from './Sidebar'
import { SChat } from './styles'

export default function Chat() {
  return (
    <SChat>
      <Navigator />
      <Sidebar />
      <ChatContainer />
    </SChat>
  )
}
