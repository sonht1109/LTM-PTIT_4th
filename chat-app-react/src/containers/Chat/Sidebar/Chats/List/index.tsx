import React, { Children } from "react";
import NoData from "src/common/components/NoData";
import ChatItem from "./ChatItem";
import { SList } from "./styles";

export default function ChatList() {
  return (
    <SList>
      {/* {Children.toArray([1, 2, 3, 4, 5, 6, 7, 8]).map((_, i) => (
        <ChatItem id={i} />
      ))} */}
      <NoData textCenter={true}>
        Bạn hiện chưa có chat box nào.
      </NoData>
    </SList>
  );
}
