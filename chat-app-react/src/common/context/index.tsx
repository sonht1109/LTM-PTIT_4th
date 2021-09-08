import { ReactNode } from "react";
import AuthProvider from "./AuthContext";
import GLProvider from "./GlobalLoadingContext";
import NavigatorProvider from "./NavigatorContext";
import CustomThemeProvider from "./ThemeContext";

export default function MyProvider({ children }: { children: ReactNode }) {
  return (
    <CustomThemeProvider>
      <GLProvider>
        <AuthProvider>
          <NavigatorProvider>{children}</NavigatorProvider>
        </AuthProvider>
      </GLProvider>
    </CustomThemeProvider>
  );
}
