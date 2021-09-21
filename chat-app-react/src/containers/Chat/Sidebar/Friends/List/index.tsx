import React, { Children } from "react";
import NoData from "src/common/components/NoData";
import { IFriend } from "src/common/ultis/types";
import { SList } from "../../Chats/List/styles";
import FriendItem from "./FriendItem";

export default function FriendList({ friends = [] }: { friends: IFriend[] }) {
  return (
    <SList>
      {friends.length ? (
        Children.toArray(friends.map((fr) => <FriendItem friend={fr} />))
      ) : (
        <NoData textCenter={true}>
          Bạn hiện chưa có bạn bè nào.
        </NoData>
      )}
    </SList>
  );
}
