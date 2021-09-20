import { Avatar } from 'antd';
import React, { useContext } from 'react'
import { FaCheck, FaUserPlus } from 'react-icons/fa';
import { avatarSrc } from 'src/common/ultis';
import { IUser } from 'src/common/ultis/types';
import { ThemeContext } from 'styled-components';
import { SResultItem } from './styles';

export default function ResultItem({user}: {user: IUser}) {
  
  const {theme} = useContext(ThemeContext)

  return (
    <SResultItem>
      <Avatar src={avatarSrc(user?.avatar || "")} className="avt" />
      <div className="detail">
        <p className="name">@{user?.username}</p>
        <div className="group-icon">
          <div className="icon">
            <FaUserPlus size={12} color="#1890ff" />
          </div>
          <div className="icon">
            <FaCheck size={12} color={theme.green} />
          </div>
        </div>
      </div>
    </SResultItem>
  )
}
