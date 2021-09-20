import { ReactNode } from "react";
import AuthProvider from "./AuthContext";
import GLProvider from "./GlobalLoadingContext";
import NavigatorProvider from "./NavigatorContext";
import CustomThemeProvider from "./ThemeContext";
import ChatboxInfoProvider from "./ChatboxInfoContext";
import ToggleSidebarProvider from "./ToggleSidebarContext";
import ReactsProvider from "./ReactContext";
import ReplyingProvider from "./ReplyingContext";

export default function MyProvider({ children }: { children: ReactNode }) {
  return (
    <CustomThemeProvider>
      <GLProvider>
        <NavigatorProvider>
          <ToggleSidebarProvider>
            <ChatboxInfoProvider>
              <ReactsProvider>
                <ReplyingProvider>
                  <AuthProvider>{children}</AuthProvider>
                </ReplyingProvider>
              </ReactsProvider>
            </ChatboxInfoProvider>
          </ToggleSidebarProvider>
        </NavigatorProvider>
      </GLProvider>
    </CustomThemeProvider>
  );
}
