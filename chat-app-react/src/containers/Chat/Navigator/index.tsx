import { Tooltip } from "antd";
import { useContext } from "react";
import {
  FaRegCommentAlt,
  FaRegMoon,
  FaRegUser,
  FaRocket,
} from "react-icons/fa";
import { NavigatorContext } from "src/common/context/NavigatorContext";
import { ETheme } from "src/common/context/ThemeContext";
import { ThemeContext } from "styled-components";
import { SNavigator, SHandleButton } from "./styles";

export default function Navigator() {
  const { theme, themeType, setTheme } = useContext(ThemeContext);

  const { index, setIndex } = useContext(NavigatorContext);

  return (
    <SNavigator>
      <div className="logo">
        <FaRocket size={30} color={theme.logo} />
      </div>
      <div className="list">

        {/* messages */}
        <Tooltip placement="right" title="Messages">
          <SHandleButton active={index === 0} onClick={() => setIndex(0)}>
            <FaRegCommentAlt
              size={16}
              color={index === 0 ? theme.icon.active : theme.icon.inactive}
            />
          </SHandleButton>
        </Tooltip>

        {/* friends */}
        <Tooltip placement="right" title="Friends">
          <SHandleButton active={index === 1} onClick={() => setIndex(1)}>
            <FaRegUser
              size={16}
              color={index === 1 ? theme.icon.active : theme.icon.inactive}
            />
          </SHandleButton>
        </Tooltip>

      </div>

      {/* theme */}
      <Tooltip placement="right" title="Toggle theme">
        <SHandleButton
          active={themeType === ETheme["DARK"]}
          onClick={setTheme}
        >
          <FaRegMoon
            size={16}
            color={themeType === ETheme["DARK"] ? theme.icon.active : theme.icon.inactive}
          />
        </SHandleButton>
      </Tooltip>
    </SNavigator>
  );
}
