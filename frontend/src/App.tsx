import { BrowserRouter } from "react-router-dom";
import { AuthProvider } from "./config/AuthContext";
import AppRoutes from "./config/AppRoutes";
import { Toaster } from "sonner";

const App = () => {
  return (
    <BrowserRouter>
      <AuthProvider>
        <AppRoutes />
        <Toaster
          position="top-center"
          expand={false}
          richColors
          duration={4000}
        />
      </AuthProvider>
    </BrowserRouter>
  );
};

export default App;
