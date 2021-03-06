import { Dropdown, Menu, Tooltip } from "antd";
import Avatar from "antd/lib/avatar/avatar";
import { useContext } from "react";
import {
  FaRegCommentAlt,
  FaRegMoon,
  FaRegUser,
  FaRocket,
} from "react-icons/fa";
import { Link, useHistory } from "react-router-dom";
import { NavigatorContext } from "src/common/context/NavigatorContext";
import { ETheme } from "src/common/context/ThemeContext";
import { ToggleSidebarContext } from "src/common/context/ToggleSidebarContext";
import { ThemeContext } from "styled-components";
import { menu } from "./store/data";
import { SNavigator, SHandleButton } from "./styles";

export default function Navigator() {
  const { theme, themeType, setTheme } = useContext(ThemeContext);

  const { index, setIndex } = useContext(NavigatorContext);
  const { toggleSidebar } = useContext(ToggleSidebarContext);

  const history = useHistory();
  const handleLogout = () => {
    localStorage.removeItem('token');
    history.replace('/login')
  }

  return (
    <SNavigator>
      <div className="logo">
        <FaRocket size={30} color={theme.logo} />
      </div>
      <div className="list">
        {/* messages */}
        <Tooltip placement="right" title="Messages">
          <SHandleButton
            active={index === 0}
            onClick={() => {
              setIndex(0);
              toggleSidebar && toggleSidebar(true);
            }}
          >
            <FaRegCommentAlt
              size={16}
              color={index === 0 ? theme.icon.active : theme.icon.inactive}
            />
          </SHandleButton>
        </Tooltip>

        {/* friends */}
        <Tooltip placement="right" title="Friends">
          <SHandleButton
            active={index === 1}
            onClick={() => {
              setIndex(1);
              toggleSidebar && toggleSidebar(true);
            }}
          >
            <FaRegUser
              size={16}
              color={index === 1 ? theme.icon.active : theme.icon.inactive}
            />
          </SHandleButton>
        </Tooltip>
      </div>

      {/* theme */}
      <Tooltip placement="right" title="Toggle theme">
        <SHandleButton active={themeType === ETheme["DARK"]} onClick={setTheme}>
          <FaRegMoon
            size={16}
            color={
              themeType === ETheme["DARK"]
                ? theme.icon.active
                : theme.icon.inactive
            }
          />
        </SHandleButton>
      </Tooltip>

      <Dropdown
        placement="topRight"
        overlay={overlay(theme, handleLogout)}
        trigger={["click"]}
      >
        <Avatar
          style={{ cursor: "pointer", marginTop: "15px" }}
          size={40}
          src="/images/avt-placeholder.png"
        />
      </Dropdown>
    </SNavigator>
  );
}

const overlay = (theme: any, handleLogout: () => void) => {
  
  return (
    <Menu>
      {menu.map((m: any, i: number) => (
        <Menu.Item className="dropdown_item" key={i} onClick={m?.callback}>
          {m?.href ? <Link to={m.href}>{m.title}</Link> : m?.title}
        </Menu.Item>
      ))}
      <Menu.Divider />
      <Menu.Item
        style={{ color: theme.badge }}
        className="dropdown_item logout"
        key={menu.length}
        onClick={handleLogout}
      >
        Log out
      </Menu.Item>
    </Menu>
  );
};
