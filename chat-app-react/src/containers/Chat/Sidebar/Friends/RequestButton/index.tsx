import { Badge, Button, Modal } from "antd";
import React, { useEffect, useState } from "react";
import { FaHistory } from "react-icons/fa";
import { requestToken } from "src/common/api/axios";
import IconButton from "src/common/components/IconButton";
import { handleError } from "src/common/ultis";
import { IFriend } from "src/common/ultis/types";
import RequestList from "./RequestList";

export default function RequestButton({ theme }: { theme: any }) {
  const [openModal, toggleModal] = useState(false);
  const [requests, setRequests] = useState<IFriend[]>([]);

  const handleCancel = () => {
    toggleModal(false);
  };

  useEffect(() => {
    requestToken({ method: "GET", url: "api/list-request" })
      .then((data) => {
        if (data.data?.body) {
          setRequests([...data.data.body]);
        }
      })
      .catch((err) => {
        handleError(err);
      });
  }, []);

  return (
    <>
      <Badge count={requests.length}>
        <IconButton
          onClick={() => toggleModal(true)}
          tooltipPosition="bottom"
          tooltipTitle="Requests"
        >
          <FaHistory color={theme.icon.inactive} size={16} />
        </IconButton>
      </Badge>
      <Modal
        footer={[
          <Button key="cancel" onClick={handleCancel}>
            Cancel
          </Button>,
        ]}
        title="List requests"
        visible={openModal}
        onCancel={handleCancel}
      >
        <RequestList requests={requests} />
      </Modal>
    </>
  );
}
