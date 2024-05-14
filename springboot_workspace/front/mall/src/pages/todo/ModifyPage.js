import { useNavigate } from "react-router-dom";

const ModifyPage = ({ tno }) => {
  const navigate = useNavigate();
  const moveToRead = () => {
    navigate({ pathname: `/todo/read/${tno}` });
  };
  const moveToList = () => {
    navigate({ pathname: `/todo/list` });
  };
  return (
    <div className="text-3xl font-extrabold">
      Todo Modify Page {tno}
      <div>
        <button onClick={() => moveToRead()}>Test Read</button>
      </div>
      <div>
        <button onClick={() => moveToList()}>Test Read</button>
      </div>
    </div>
  );
};
export default ModifyPage;
