import Avatar from "antd/lib/avatar/avatar";
import { FaEye } from "react-icons/fa";
import { SChatBody, SMessage } from "../styles";

export default function ChatBody() {

  return (
    <SChatBody>
      <div className="top"></div>

      <SMessage fromMe={true}>
        {/* <Avatar src="/images/avt-placeholder.png" size={36} /> */}
        <div className="detail">
          {/* <p className="name">Vii yeu quai</p> */}
          <div className="content">
            Ex amet culpa non cillum ad cillum dolore.
            <div className="timestamp">
              <FaEye size={10} color="white" />
              <span>11:08 PM</span>
            </div>
          </div>
        </div>
      </SMessage>

      <SMessage fromMe={false} className="is_last">
        <Avatar src="/images/avt-placeholder.png" size={36} />
        <div className="detail">
          <p className="name">Vii yeu quai</p>
          <div className="content">
            Ex amet culpa non cillum ad cillum dolore.
            <div className="timestamp">
              <span>11:08 PM</span>
            </div>
          </div>
        </div>
      </SMessage>

      <SMessage fromMe={false} className="is_first">
        <Avatar src="/images/avt-placeholder.png" size={36} />
        <div className="detail">
          <p className="name">Vii yeu quai</p>
          <div className="content">
            Ex amet culpa non cillum ad cillum dolore.
            <div className="timestamp">
              <span>11:08 PM</span>
            </div>
          </div>
        </div>
      </SMessage>
      <div className="bottom"></div>
    </SChatBody>
  );
}
