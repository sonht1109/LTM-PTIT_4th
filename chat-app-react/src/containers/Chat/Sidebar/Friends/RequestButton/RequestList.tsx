import React from 'react'
import NoData from 'src/common/components/NoData'
import { IFriend } from 'src/common/ultis/types'
import styled from 'styled-components'
import RequestItem from './RequestItem'

export default function RequestList({requests = []}: {requests: IFriend[]}) {
  return (
    <SList>
      {requests.length ? React.Children.toArray(requests.map((r: IFriend) => (
        <RequestItem request={r} />
      ))) : <NoData textCenter={false}>Bạn chưa có lời mời kết bạn nào.</NoData>}
    </SList>
  )
}

const SList = styled.div``