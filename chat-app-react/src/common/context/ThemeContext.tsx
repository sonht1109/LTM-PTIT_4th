import { ReactNode, useState } from "react";
import { ThemeProvider } from "styled-components";
import theme from "../styles/theme";

export enum ETheme {
  LIGHT = "light",
  DARK = "dark",
}

const CustomThemeProvider = ({ children }: { children: ReactNode }) => {
  const [themeType, setThemeType] = useState<ETheme>(ETheme["LIGHT"]);

  const setTheme = () => {
    setThemeType((prev) =>
      prev === ETheme["LIGHT"] ? ETheme["DARK"] : ETheme["LIGHT"]
    );
  };

  return (
    <ThemeProvider theme={{ theme: theme[themeType], setTheme, themeType }}>
      {children}
    </ThemeProvider>
  );
};

export default CustomThemeProvider;
