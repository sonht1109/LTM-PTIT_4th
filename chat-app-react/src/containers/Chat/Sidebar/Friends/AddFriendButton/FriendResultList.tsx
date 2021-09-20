import React, { Children } from 'react'
import { IUser } from 'src/common/ultis/types'
import styled from 'styled-components'
import ResultItem from './FriendResultItem'

export default function FriendResultList({users}: {users: IUser[]}) {
  return (
    <SList>
      {Children.toArray(users.map((user) =>  (
        <ResultItem user={user} />
      )))}
    </SList>
  )
}

const SList = styled.div``