import React, { Children, useContext } from "react";
import { AuthContext } from "src/common/context/AuthContext";
import { IUser } from "src/common/ultis/types";
import styled from "styled-components";
import ResultItem from "./FriendResultItem";

export default function FriendResultList({ users }: { users: IUser[] }) {
  const { info } = useContext(AuthContext);

  return (
    <SList>
      {Children.toArray(
        users.map((user) => {
          if (user.id !== info.id) return <ResultItem user={user} />;
          return null;
        })
      )}
    </SList>
  );
}

const SList = styled.div``;
