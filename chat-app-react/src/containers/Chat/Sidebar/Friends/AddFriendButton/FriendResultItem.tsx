import { Avatar, message } from 'antd';
import React, { useContext } from 'react'
import { FaCheck, FaUserPlus } from 'react-icons/fa';
import { requestToken } from 'src/common/api/axios';
import { avatarSrc, handleError } from 'src/common/ultis';
import { IUser } from 'src/common/ultis/types';
import { ThemeContext } from 'styled-components';
import { SResultItem } from './styles';

export default function ResultItem({user}: {user: IUser}) {
  
  const {theme} = useContext(ThemeContext)

  const handleAddFriend = () => {
    requestToken({method: "POST", url: "api/add-friend", data: {
        friend_id: user.id
    }})
    .then(data => {
      if(data.data?.code === '200') {
        message.success(data.data?.message);
      }
      else {
        message.error(data.data?.message)
      }
    })
    .catch(err => {
      handleError(err)
    })
  }

  return (
    <SResultItem>
      <Avatar src={avatarSrc(user?.avatar || "")} className="avt" />
      <div className="detail">
        <p className="name">@{user?.username}</p>
        <div className="group-icon">
          <div className="icon" onClick={handleAddFriend}>
            <FaUserPlus size={12} color="#1890ff" />
          </div>
          {/* <div className="icon">
            <FaCheck size={12} color={theme.green} />
          </div> */}
        </div>
      </div>
    </SResultItem>
  )
}
