import { Button, Input, Modal } from "antd";
import React, { useState } from "react";
import { FaUserPlus } from "react-icons/fa";
import { requestToken } from "src/common/api/axios";
import IconButton from "src/common/components/IconButton";
import { handleError } from "src/common/ultis";
import { IUser } from "src/common/ultis/types";
import FriendResultList from './FriendResultList'

export default function AddFriendButton({ theme }: { theme: any }) {

  const [openModal, toggleModal] = useState(false);
  const [users, setUsers] = useState<IUser[]>([])

  const handleCancel = () => {
    toggleModal(false)
  }

  const onSearch = (data: string) => {
    requestToken({method: "GET", url: "api/list-user", params: {
      username: data
    }})
    .then(data => {
      if(data?.data?.body) {
        const res = data.data.body;
        setUsers([...res]);
      }
    })
    .catch(err => {
      handleError(err)
    })
  };

  return (
    <>
      <IconButton
        onClick={() => toggleModal(true)}
        tooltipPosition="bottom"
        tooltipTitle="Add a new friend"
      >
        <FaUserPlus color={theme.icon.inactive} size={16} />
      </IconButton>
      <Modal
        footer={[
          <Button key="cancel" onClick={handleCancel}>
            Cancel
          </Button>,
        ]}
        title="Add a new friend"
        visible={openModal}
        onCancel={handleCancel}
      >
        <Input.Search
          name="username"
          enterButton
          onSearch={onSearch}
        />
        <FriendResultList users={users} />
      </Modal>
    </>
  );
}
