import styles from "./modal.module.css";
import { useRouter } from "next/router";

interface ModalProps {
    children?: React.ReactNode;
    setModalOpen: React.Dispatch<React.SetStateAction<boolean>>
    title: string
}

export default function Modal({ children, setModalOpen, title } : ModalProps) {

    const router = useRouter();

    return (
        <div className={styles.modalOverlay}>
            <form className={styles.modal} method="POST">
                <h2>{title}</h2>

                {children}

                <div className={styles.buttons}>
                    <button type="submit">Salvar</button>
                    <button type="button" onClick={() => setModalOpen(false)}>Voltar</button>
                </div>
            </form>
        </div>
    );
}