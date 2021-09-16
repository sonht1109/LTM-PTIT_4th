import { ReactNode } from "react";
import AuthProvider from "./AuthContext";
import GLProvider from "./GlobalLoadingContext";
import NavigatorProvider from "./NavigatorContext";
import CustomThemeProvider from "./ThemeContext";
import ChatboxInfoProvider from "./ChatboxInfoContext";
import ToggleSidebarProvider from "./ToggleSidebarContext";
import ReactsProvider from "./ReactContext";

export default function MyProvider({ children }: { children: ReactNode }) {
  return (
    <CustomThemeProvider>
      <GLProvider>
        <AuthProvider>
          <NavigatorProvider>
            <ToggleSidebarProvider>
              <ChatboxInfoProvider>
                <ReactsProvider>{children}</ReactsProvider>
              </ChatboxInfoProvider>
            </ToggleSidebarProvider>
          </NavigatorProvider>
        </AuthProvider>
      </GLProvider>
    </CustomThemeProvider>
  );
}
